package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}

case class EachRelation(
  id:        Option[User.Id],                           // 自分のID 
  targetId:  Option[User.Id],                           // 相手のID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object EachRelation{
  type WithNoId   = Entity.WithNoId   [Id, EachRelation]
  type EmbeddedId = Entity.EmbeddedId [Id, EachRelation]
}
