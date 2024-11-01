/*
rule = AsUnitIsVoid
*/
package test

import cats.Functor
import cats.syntax.all._

object AsUnitIsVoid {
  type F[A] = Option[A]
  val fa: Option[Int] = None
  val f: Int => Option[Int] = Some(_)
  val g: Int => Option[String] = _ => None

  // Тривиальный случай (method syntax)
  fa.as(())

  // Тривиальный случай (function syntax)
  Functor[F].as(fa, ())

  // Когда F[A] композитная
  fa.flatMap(f).flatMap(g).as(())
}
