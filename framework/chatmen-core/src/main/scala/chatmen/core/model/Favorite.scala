/* package chatmen.core.model

import ixias.model._
import java.time.LocalDateTime
import chatmen.udb.model.User

case class Favorite(
  id:        Option[User.Id],                          // UserID
  tweetId:   Option[Tweet.Id],                         // tweetID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object Favorite {
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [User.Id, Favorite]
  type EmbeddedId = Entity.EmbeddedId [User.Id, Favorite]
}

 */