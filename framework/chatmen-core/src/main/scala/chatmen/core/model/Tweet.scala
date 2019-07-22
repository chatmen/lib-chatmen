package chatmen.core.model

import ixias.model._
import java.time.LocalDateTime
import chatmen.udb.model.User

import Tweet._
case class Tweet(
  id:             Option[Id],                     // TweetID
  uid:            Option[User.Id],                      // UserID
  text:           String,                               // text本文
  favoriteNumber: Int               = 0,
  reTweetNumber:  Int               = 0,
  updatedAt:      LocalDateTime     = NOW,              // データ更新日
  createdAt:      LocalDateTime     = NOW               // データ作成日
)extends EntityModel[Id]

object Tweet {
  // --[ エンティティ定義 ]-----------------------------------------------------
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ Tweet
  type WithNoId   = Entity.WithNoId   [Id, Tweet]
  type EmbeddedId = Entity.EmbeddedId [Id, Tweet]

  // --[ オブジェクトの生成 ]---------------------------------------------------
  object WithNoId{
    def apply(uid: User.Id,text:String): WithNoId =
      Entity.WithNoId { Tweet(None,Some(uid),text) }
  }
}
