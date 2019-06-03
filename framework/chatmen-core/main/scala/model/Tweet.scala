package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}
import User._

case class Tweet(
  id:        Option[Tweet.Id],                         // TweetID
  userId:    Option[User.Id],                          // UserID
  text:      Option[String],                           // text本文
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Tweet.Id]

object Tweet {
  // --[ エンティティ定義 ]-----------------------------------------------------
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ Tweet
  type WithNoId   = Entity.WithNoId   [Id, Tweet]
  type EmbeddedId = Entity.EmbeddedId [Id, Tweet]
}
