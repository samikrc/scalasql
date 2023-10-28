package scalasql
import operations.{
  ExprBooleanOpsTests,
  ExprNumericOpsTests,
  ExprSeqNumericOpsTests,
  ExprSeqOpsTests,
  ExprStringOpsTests
}
import query.{
  InsertTests,
  DeleteTests,
  SelectTests,
  SubQueryTests,
  UpdateTests,
  UpdateJoinTests,
  UpdateSubQueryTests,
  ReturningTests,
  OnConflictTests
}
import scalasql.dialects.{
  HsqlDbDialectTests,
  MySqlDialectTests,
  PostgresDialectTests,
  SqliteDialectTests,
  H2DialectTests
}

package mysql {
  object ExprBooleanOpsTests extends ExprBooleanOpsTests with MySqlSuite
  object ExprExprIntOpsTests extends ExprNumericOpsTests with MySqlSuite
  object ExprSeqNumericOpsTests extends ExprSeqNumericOpsTests with MySqlSuite
  object ExprSeqOpsTests extends ExprSeqOpsTests with MySqlSuite
  object ExprStringOpsTests extends ExprStringOpsTests with MySqlSuite
  object InsertTests extends InsertTests with MySqlSuite
  object DeleteTests extends DeleteTests with MySqlSuite
  object SelectTests extends SelectTests with MySqlSuite
  object SubQueryTests extends SubQueryTests with MySqlSuite
  object UpdateTests extends UpdateTests with MySqlSuite
  object UpdateJoinTests extends UpdateJoinTests with MySqlSuite
  // MySql does not support updates with subqueries referencing same table
  // object UpdateSubQueryTests extends UpdateSubQueryTests with MySqlSuite
  // MySql does not support INSERT/UPDATE RETURNING
  // object ReturningTests extends ReturningTests with MySqlSuite
  // MySql does not support onConflictIgnore and onConflictUpdate does not take columns
  // object OnConflictTests extends OnConflictTests with MySqlSuite

  object MySqlDialectTests extends MySqlDialectTests

  object DataTypesTests extends datatypes.DataTypesTests with MySqlSuite
}

package postgres {
  object ExprBooleanOpsTests extends ExprBooleanOpsTests with PostgresSuite
  object ExprExprIntOpsTests extends ExprNumericOpsTests with PostgresSuite
  object ExprSeqNumericOpsTests extends ExprSeqNumericOpsTests with PostgresSuite
  object ExprSeqOpsTests extends ExprSeqOpsTests with PostgresSuite
  object ExprStringOpsTests extends ExprStringOpsTests with PostgresSuite
  object InsertTests extends InsertTests with PostgresSuite
  object DeleteTests extends DeleteTests with PostgresSuite
  object SelectTests extends SelectTests with PostgresSuite
  object SubQueryTests extends SubQueryTests with PostgresSuite
  object UpdateTests extends UpdateTests with PostgresSuite
  object UpdateJoinTests extends UpdateJoinTests with PostgresSuite
  object UpdateSubQueryTests extends UpdateSubQueryTests with PostgresSuite
  object ReturningTests extends ReturningTests with PostgresSuite
  object OnConflictTests extends OnConflictTests with PostgresSuite

  object PostgresDialectTests extends PostgresDialectTests

  object DataTypesTests extends datatypes.DataTypesTests with PostgresSuite
}

package sqlite {
  object ExprBooleanOpsTests extends ExprBooleanOpsTests with SqliteSuite
  object ExprIntOpsTests extends ExprNumericOpsTests with SqliteSuite
  object ExprSeqNumericOpsTests extends ExprSeqNumericOpsTests with SqliteSuite
  object ExprSeqOpsTests extends ExprSeqOpsTests with SqliteSuite
  object ExprStringOpsTests extends ExprStringOpsTests with SqliteSuite
  object InsertTests extends InsertTests with SqliteSuite
  object DeleteTests extends DeleteTests with SqliteSuite
  object SelectTests extends SelectTests with SqliteSuite
  object SubQueryTests extends SubQueryTests with SqliteSuite
  object UpdateTests extends UpdateTests with SqliteSuite
  object UpdateJoinTests extends UpdateJoinTests with SqliteSuite
  object UpdateSubQueryTests extends UpdateSubQueryTests with SqliteSuite
  object ReturningTests extends ReturningTests with SqliteSuite
  object OnConflictTests extends OnConflictTests with SqliteSuite

  object SqliteDialectTests extends SqliteDialectTests

  object DataTypesTests extends datatypes.DataTypesTests with SqliteSuite
}

package hsqldb {
  object ExprBooleanOpsTests extends ExprBooleanOpsTests with HsqlDbSuite
  object ExprIntOpsTests extends ExprNumericOpsTests with HsqlDbSuite
  object ExprSeqNumericOpsTests extends ExprSeqNumericOpsTests with HsqlDbSuite
  object ExprSeqOpsTests extends ExprSeqOpsTests with HsqlDbSuite
  object ExprStringOpsTests extends ExprStringOpsTests with HsqlDbSuite
  object InsertTests extends InsertTests with HsqlDbSuite
  object DeleteTests extends DeleteTests with HsqlDbSuite
  object SelectTests extends SelectTests with HsqlDbSuite
  object SubQueryTests extends SubQueryTests with HsqlDbSuite
  object UpdateTests extends UpdateTests with HsqlDbSuite
  // HSql does not support UPDATE/JOIN keywords
  // object UpdateJoinTests extends UpdateTests with HsqlDbSuite
  object UpdateSubQueryTests extends UpdateSubQueryTests with HsqlDbSuite
  // HSql does not support RETURNING keyword
  // object ReturningTests extends ReturningTests with HsqlSuite
  // HSql does not support ON CONFLICT IGNORE
  // object OnConflictTests extends OnConflictTests with H2Suite

  object HsqlDbDialectTests extends HsqlDbDialectTests

  object DataTypesTests extends datatypes.DataTypesTests with HsqlDbSuite
}

package h2 {
  object ExprBooleanOpsTests extends ExprBooleanOpsTests with H2Suite
  object ExprIntOpsTests extends ExprNumericOpsTests with H2Suite
  object ExprSeqNumericOpsTests extends ExprSeqNumericOpsTests with H2Suite
  object ExprSeqOpsTests extends ExprSeqOpsTests with H2Suite
  object ExprStringOpsTests extends ExprStringOpsTests with H2Suite
  object InsertTests extends InsertTests with H2Suite
  object DeleteTests extends DeleteTests with H2Suite
  object SelectTests extends SelectTests with H2Suite
  object SubQueryTests extends SubQueryTests with H2Suite
  object UpdateTests extends UpdateTests with H2Suite
  object UpdateJoinTests extends UpdateTests with H2Suite
  object UpdateSubQueryTests extends UpdateSubQueryTests with H2Suite
  // H2 does not support RETURNING keyword
  // object ReturningTests extends ReturningTests with H2Suite
  // H2 does not support ON CONFLICT IGNORE unless in postgres mode
  // object OnConflictTests extends OnConflictTests with H2Suite

  object HsqlDbDialectTests extends H2DialectTests

  object DataTypesTests extends datatypes.DataTypesTests with H2Suite
}
