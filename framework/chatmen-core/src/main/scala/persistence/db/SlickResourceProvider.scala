package chatmen.core.persistence.db


package chatmen.udb.persistence.db
import chatmen.udb.persistence.db.UserTable
import slick.jdbc.JdbcProfilerofile

trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val driver: P

  // --[ テーブル定義 ] --------------------------------------------------------
  object TweetTable                  extends TweetTable
  object ReTweetTable                extends ReTweetTable
  object FavoriteTable               extends FavoriteTable

  // --[ 全てのテーブル定義 ] --------------------------------------------------
  lazy val AllTables = Seq(
    UserTable,
    ReTweet,
    Favorite
  )
}
