package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}
import User._

case class UserBlock(
  id:        Option[User.Id],                          // ユーザID
  targetId:  Option[User.Id],                          // ターゲットID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日  
)extends EntityModel[User.Id]

object UserBlock{
  type WithNoId   = Entity.WithNoId   [Id, UserBlock]
  type EmbeddedId = Entity.EmbeddedId [Id, UserBlock]
}
