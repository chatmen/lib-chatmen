/* package chatmen.core.model

import ixias.model._
import java.time.LocalDateTime
import chatmen.udb.model.User

case class ReTweet(
  id:        ReTweet.Id,                               // RetweetID
  tid:       Tweet.Id,                                 // TweetID
  uid:       User.Id,                                  // UserID
  targetId:  User.Id,                                  // UserID
  text:      String,                                   // tweet内容
  updatedAt: LocalDateTime         = NOW,              // データ更新日
  createdAt: LocalDateTime         = NOW               // データ作成日
)extends EntityModel[User.Id]

object ReTweet{
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [User.Id, ReTweet]
  type EmbeddedId = Entity.EmbeddedId [User.Id, ReTweet]
}
 */