package chatmen.core.persistence.db

import slick.jdbc.JdbcProfile
trait SlickResourceProvider[P <: JdbcProfile] {

  implicit val driver: P

  // --[ テーブル定義 ] --------------------------------------------------------
  object TweetTable                  extends TweetTable
  //object ReTweetTable                extends ReTweetTable
  //object FavoriteTable               extends FavoriteTable

  // --[ 全てのテーブル定義 ] --------------------------------------------------
  lazy val AllTables = Seq(
    TweetTable
    //ReTweet,
    //Favorite
  )
}
