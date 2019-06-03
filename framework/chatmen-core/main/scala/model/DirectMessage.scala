package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}
import User._
import Tweet._

case class DirectMessage(
  id:        Option[User.Id],                          // UserID
  message:   Option[String],                           // TweetID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object DirectMessage{
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [Id, DirectMessage]
  type EmbeddedId = Entity.EmbeddedId [Id, DirectMessage]
}
