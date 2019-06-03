package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}

case class Favorite(
  id: Option[User.Id],                                 // UserID
  tweetId:   Option[Tweet.Id],                         // tweetID
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object Favorite {
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [Id, Favorite]
  type EmbeddedId = Entity.EmbeddedId [Id, Favorite]
}
