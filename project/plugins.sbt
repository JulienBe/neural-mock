// for box2dlights
// https://github.com/libgdx/box2dlights/issues/103
checksums in update := Nil

resolvers += Resolver.url("scalasbt snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.hanhuy.sbt" % "android-sdk-plugin" % "1.3.6")

