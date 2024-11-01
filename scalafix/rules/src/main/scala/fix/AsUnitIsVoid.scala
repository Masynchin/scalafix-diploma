package fix

import metaconfig.Configured
import scalafix.v1._
import scala.meta._

class AsUnitIsVoid extends SemanticRule("AsUnitIsVoid") {
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree
      .collect {
        // method syntax: fa.as(())
        case ta @ Term.Apply(Term.Select(_, name @ Term.Name("as")), List(Lit.Unit())) =>
          Patch.replaceTree(name, "void") + Patch.replaceTree(ta.argClause, "")

        // typeclass method syntax: Functor[F].as(fa, ())
        case ta @ Term.Apply(Term.Select(_, name @ Term.Name("as")), List(fa, Lit.Unit())) =>
          Patch.replaceTree(name, "void") + Patch.replaceTree(ta.argClause, s"(${fa.toString})")
      }
      .asPatch
  }
}

