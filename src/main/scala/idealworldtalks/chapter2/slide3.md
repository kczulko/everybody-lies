# HOLD YOUR HORSES!!! HOW IT RESOLVES???

```scala
1)      LazyList(1,2,3,4).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
2)      LazyCons(11, LazyList(2,3,4)).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
3)      LazyList(2,3,4).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
4)      LazyCons(12, LazyList(3,4)).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
5)      12 :: LazyList(3,4).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
6)      12 :: LazyCons(3, LazyList(4)).map(_ + 10).filter(_ % 2 == 0).toList
```
 ```scala
7)      12 :: LazyCons(4, LazyNil).map(_ + 10).filter(_ % 2 == 0).toList
```

```scala
8)      12 :: 14 :: LazyNil.map(_ + 10).filter(_ % 2 == 0).toList
```
 
```scala
9)      12 :: 14 :: Nil
```

### TO BE HONEST OUR `LAZY LIST` HAS SOME SPECIAL NAME IN FP...