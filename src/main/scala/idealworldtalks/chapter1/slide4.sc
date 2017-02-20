

val nashList = List(1,2,3,4)

def makeString(l: List[Int]): String =
  l match {
    case h :: t => h.toString + makeString(t)
    case _ => ""
  }

def sum(l: List[Int]): Int =
  l match {
    case h :: t => h + sum(t)
    case _ => 0
  }

def reduce[A, B](l: List[A], zero: B)(f: (A,B) => B): B =
  l match {
    case h :: t => f(h, reduce(t, zero)(f))
    case _ => zero
  }

makeString(nashList)
sum(nashList)

def makeString2(l: List[Int]): String = reduce(l, "")(_.toString + _)
makeString2(nashList)
def sum2(l: List[Int]): Int = reduce(l, 0)(_ + _)


def map[A,B](l: List[A])(f: A => B): List[B] =
  reduce(l, List[B]())(f(_) :: _)

def flatMap[A,B](l: List[A])(f: A => List[B]): List[B] =
  reduce(l, List[B]())(f(_) ::: _)

def flatten[A](l: List[List[A]]) =
  flatMap(l)(identity)
























