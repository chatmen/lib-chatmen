package chatmen.udb.model

import ixias.model._
import java.time.LocalDateTime

import chatmen.udb.model.UserEachRelation._
case class UserEachRelation(
  id:         Option[Id],                               // PrimaryID
  fromId:     User.Id,                                  // 自分のID
  targetId:   User.Id,                                  // 相手のID
  updatedAt:  LocalDateTime         = NOW,              // データ更新日
  createdAt:  LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Id]

object UserEachRelation{
  val  Id         = the[Identity[Id]]
  type Id         = Long @@ UserEachRelation
  type WithNoId   = Entity.WithNoId   [Id, UserEachRelation]
  type EmbeddedId = Entity.EmbeddedId [Id, UserEachRelation]

  // --[ オブジェクトの生成 ]---------------------------------------------------
  object WithNoId{
    def apply(fromId: User.Id, targetId: User.Id): WithNoId =
      Entity.WithNoId {UserEachRelation(None, fromId, targetId) }
  }
}
