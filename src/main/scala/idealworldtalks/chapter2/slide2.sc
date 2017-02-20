
sealed trait LazyList[+A] {
  def head: () => A
  def tail: () => LazyList[A]

  def map[B](f: A => B): LazyList[B]
  def filter(f: A => Boolean): LazyList[A]

  def toList: List[A] =
    this match {
      case LazyCons(h,t) => h() :: t().toList
      case _ => List()
    }
}

case class LazyCons[A](head: () => A, tail: () => LazyList[A]) extends LazyList[A] {
  override def map[B](f: (A) => B): LazyList[B] =
    LazyCons(() => f(head()), () => tail().map(f))

  override def filter(f: (A) => Boolean): LazyList[A] =
    if (f(head())) {
      LazyCons(head, () => tail().filter(f))
    } else {
      tail().filter(f)
    }
}

case object LazyNil extends LazyList[Nothing] {
  override def head: () => Nothing = throw new NoSuchElementException(":(")
  override def tail: () => LazyList[Nothing] = throw new NoSuchElementException(":(")

  override def map[B](f: (Nothing) => B): LazyList[B] = this
  override def filter(f: (Nothing) => Boolean): LazyList[Nothing] = this
}

object LazyList {
  def apply[A](as: A*): LazyList[A] =
    if (as.isEmpty)
      LazyNil
    else
      LazyCons(() => as.head, () => apply(as.tail : _*))
}

val nashList = LazyList(1,2,3,4)
    .map(_ + 10)
    .filter(_ % 2 == 0)
      .toList



