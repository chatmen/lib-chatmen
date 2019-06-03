package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}
import user._

case class ReTweet(
  id:        Option[User.Id],                          // UserID
  targetId:  Option[User.Id],                          // UserID
  text:      String,
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日  
)extends EntityModel[Id]

object ReTweet{
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [User.Id, ReTweet]
  type EmbeddedId = Entity.EmbeddedId [User.Id, ReTweet]
}
