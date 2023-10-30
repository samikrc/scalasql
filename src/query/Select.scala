package scalasql.query

import scalasql.renderer.SqlStr.SqlStringSyntax
import scalasql.renderer.{Context, SelectToSql, SqlStr}
import scalasql.{MappedType, Queryable}
import scalasql.utils.OptionPickler

trait Select[Q, R]
    extends SqlStr.Renderable
    with Aggregatable[Q]
    with From
    with Joinable[Q, R]
    with JoinOps[Select, Q, R]
    with Query.Multiple[R] {

  def newCompoundSelect[Q, R](
      lhs: SimpleSelect[Q, R],
      compoundOps: Seq[CompoundSelect.Op[Q, R]],
      orderBy: Option[OrderBy],
      limit: Option[Int],
      offset: Option[Int]
  )(implicit qr: Queryable[Q, R]): CompoundSelect[Q, R] =
    new CompoundSelect(lhs, compoundOps, orderBy, limit, offset)

  def newSimpleSelect[Q, R](
      expr: Q,
      exprPrefix: Option[String],
      from: Seq[From],
      joins: Seq[Join],
      where: Seq[Expr[_]],
      groupBy0: Option[GroupBy]
  )(implicit qr: Queryable[Q, R]): SimpleSelect[Q, R] =
    new SimpleSelect(expr, exprPrefix, from, joins, where, groupBy0)

  def qr: Queryable[Q, R]
  def isTrivialJoin: Boolean = false
  def select = this

  def distinct: Select[Q, R]

  def simple(args: Iterable[_]*) = args.forall(_.isEmpty)

  def subquery(implicit qr: Queryable[Q, R]) = new SubqueryRef[Q, R](this, qr)

  def map[Q2, R2](f: Q => Q2)(implicit qr: Queryable[Q2, R2]): Select[Q2, R2]
  def flatMap[Q2, R2](f: Q => Select[Q2, R2])(implicit qr: Queryable[Q2, R2]): Select[Q2, R2]
  def filter(f: Q => Expr[Boolean]): Select[Q, R]

  def aggregate[E, V](f: SelectProxy[Q] => E)(implicit qr: Queryable[E, V]): Aggregate[E, V]

  def groupBy[K, V, R2, R3](groupKey: Q => K)(
      groupAggregate: SelectProxy[Q] => V
  )(implicit qrk: Queryable[K, R2], qrv: Queryable[V, R3]): Select[(K, V), (R2, R3)]

  def sortBy(f: Q => Expr[_]): Select[Q, R]
  def asc: Select[Q, R]
  def desc: Select[Q, R]
  def nullsFirst: Select[Q, R]
  def nullsLast: Select[Q, R]

  def union(other: Select[Q, R]): Select[Q, R] = compound0("UNION", other)
  def unionAll(other: Select[Q, R]): Select[Q, R] = compound0("UNION ALL", other)
  def intersect(other: Select[Q, R]): Select[Q, R] = compound0("INTERSECT", other)
  def except(other: Select[Q, R]): Select[Q, R] = compound0("EXCEPT", other)
  def compound0(op: String, other: Select[Q, R]): CompoundSelect[Q, R]

  def drop(n: Int): Select[Q, R]
  def take(n: Int): Select[Q, R]

  def toSqlQuery(implicit ctx: Context): (SqlStr, Seq[MappedType[_]]) = {
    val (_, sqlStr, _, mappedTypes) = toSqlQuery0(ctx)

    (sqlStr.withCompleteQuery(true), mappedTypes)
  }
  def walk() = qr.walk(expr)
  override def singleRow = false

  def toSqlQuery0(
      prevContext: Context
  ): (Map[Expr.Identity, SqlStr], SqlStr, Context, Seq[MappedType[_]])

  def single: Query.Single[R] = new Query.Single(this)

  def simpleFrom[Q, R](s: Select[Q, R]) = s match {
    case s: SimpleSelect[Q, R] => s
    case s: CompoundSelect[Q, R] =>
      newSimpleSelect(s.expr, None, Seq(s.subquery(s.qr)), Nil, Nil, None)(s.qr)
  }
}

