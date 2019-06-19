/*package chatmen.core.model

import ixias.model._
import java.time.LocalDateTime

import chatmen.udb.model.User

import DirectMessage._
case class DirectMessage(
  id:        Option[User.Id],                          // UserID
  targetId:  Option[User.Id],                          // 相手のID
  text:      Option[String]        = None,             // テキスト
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object DirectMessage{
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [User.Id, DirectMessage]
  type EmbeddedId = Entity.EmbeddedId [User.Id, DirectMessage]
}
 */
