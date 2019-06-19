package chatmen.core.model

import ixias.model._
import java.time.LocalDateTime
import chatmen.udb.model.User

import Tweet._
case class Tweet(
  id:        Option[Tweet.Id],                               // TweetID
  userId:    Option[User.Id],                          // UserID
  text:      Option[String]        = None,             // text本文
  favoriteNumber : Option[Int]     = None,
  reTweetNumber : Option[Int]      = None,
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Id]

object Tweet {
  // --[ エンティティ定義 ]-----------------------------------------------------
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ Tweet
  type WithNoId   = Entity.WithNoId   [Id, Tweet]
  type EmbeddedId = Entity.EmbeddedId [Id, Tweet]

  // --[ オブジェクトの生成 ]---------------------------------------------------
  object WithNoId{
    def apply(uid: User.Id): WithNoId =
      Entity.WithNoId { Tweet(None,Some(uid)) }
  }
}
