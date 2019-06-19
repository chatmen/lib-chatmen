// package chatmen.core.model

// import ixias.model._
// import java.time.LocalDateTime
// import Tweet._
// import chatmen.udb.model.User

// import ReturnMessage._
// case class ReturnMessage(
//   id:                 Option[Tweet.Id],                         // TweetID
//   userid:             Option[User.Id],                          // UseriD
//   text:               Option[String]       = None,              // Text本文
//   updatedAt:          LocalDateTime         = NOW,              // データ更新日
//   createdAt:          LocalDateTime         = NOW               // データ作成日
// )extends EntityModel[User.Id]

// object  ReturnMessage{
//   // --[ エンティティ定義 ]-----------------------------------------------------
//   type WithNoId   = Entity.WithNoId   [Id, ReturnMessage]
//   type EmbeddedId = Entity.EmbeddedId [Id, ReturnMessage]

//   // --[ オブジェクトの生成 ]---------------------------------------------------
//   def apply(tid: Tweet.Id, uid: User.Id): EmbeddedId =
//     Entity.EmbeddedId {ReturnMessage(Some(id), Some(userid)) }
// }
