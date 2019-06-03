package chatmen.udb.model

import ixias.model._
import java.time.{LocalDate, LocalDateTime}
import Tweet._
import User._


case class ReturnMessage(
  id:                 Option[Tweet.Id],                         // TweetID
  userId:             Option[User.Id],                          // UseriD
  text:               Option[String],                           // Text本文
  isLimitTweetLength: Boolean,                                  // 文字数制限
  updatedAt:          LocalDateTime         = NOW,              // データ更新日
  createdAt:          LocalDateTime         = NOW               // データ作成日
)extends EntityModel[Tweet.Id]

object  ReturnMessage{
  // --[ エンティティ定義 ]-----------------------------------------------------
  type WithNoId   = Entity.WithNoId   [Id, ReturnMessage]
  type EmbeddedId = Entity.EmbeddedId [Id, ReturnMessage]

  // // --[ オブジェクトの生成 ]---------------------------------------------------
  // def apply(tid: Tweet.Id, uid: User.Id): EmbeddedId =
  //   Entity.EmbeddedId { ReturnMessage(Some(id)) }
}
