package chatmen.udb.model

import ixias.model._
import java.time.LocalDateTime

case class UserEachRelation(
  id:        Option[User.Id],                           // 自分のID 
  targetId:  Option[User.Id],                           // 相手のID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object UserEachRelation{
  type WithNoId   = Entity.WithNoId   [User.Id, UserEachRelation]
  type EmbeddedId = Entity.EmbeddedId [User.Id, UserEachRelation]
}
