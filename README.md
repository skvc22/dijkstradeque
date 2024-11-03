[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6646008&assignment_repo_type=AssignmentRepo)
# Dijkstra's Deque
This final project for Advanced CS is actually _two_ different projects. The
first task is to code what is called a _Deque_ (see below) and the second is
to use a stack (which you have already coded, of course) to implement
Dijkstra's Algorithm for the evaluation of algebraic expressions (also below).
Please read all of the specifications carefully before you begin.

## Deque

The word "Deque" is an abbreviation for "Double-Ended Queue" and is a kind of
cross between stacks and queues. Their distinguishing feature is that items can
be added and taken from _both_ sides. For the sake of this project we will term
these sides _left_ and _right_, but they are sometimes called "start" and "end"
as well.


Your Deque must be fully generic (it must make sufficient use of generic type
variables in its definition, and implement the ``Iterable<T>`` interface in
addition to the following API:

`public Deque()` which constructs an empty Deque of type T.

`public boolean isEmpty()` which returns whether or not the Deque is currently
empty (contains no elements). This is true for a newly-initialized Deque (and
other empty deques).

`public int size()` which returns the number of elements in the deque.

`public void addLeft(T item)` which adds the specified item to the _left_ end
of the dequeue.

`public void addRight(T item)` which adds the specified item to the _right_
end of the deque.

`public T removeLeft()` which returns and removes the leftmost item in the
deque.

`public T removeRight()` which returns and removes the rightmost item in the
deque.

`public Iterator<T> iterator()` which returns an iterator over the elements
of the deque. **Important**: for the sake of this project the _left_ end is
considered to be the start of the deque and the right the end. Hence an
iterator over the deque should return the elements in _left-to-right_ order.

### Some Final Notes About Deques

Not specified above are the methods and constructors for internal classes you
may need. For example, you will need to have an ``Iterator`` as part of your
class since your class implements the ``Iterable`` interface. You will want
to consult the [javadocs](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)
for the ``Iterator`` interface to find the methods that it must implement.
Remember that you do not need to override _default_ methods.

Also, while you can make a deque using an array (or arrays) instead of a linked
list, please **Do not use an array** for this project. It may _seem_ easier at
first, but your life will be absolutely miserable when you get to the resizing
portion of the code. For example, you will not only need to detect when there
are too many (or too few) elements in the deque, but also when the left and
right sides are colliding with eachother. There are other problems too, but you
can avoid them by **using a linked list!!!** You're welcome.

## World's Worst Compiler

The second part of your project is to implement a version of Dijkstra's
Algorithm for the evaluation of algebraic expressions. Specifically, you will
write a class called ``Calculator`` which computes the value of an arithmetic
expression subject to the following syntax rules.

1. Only _binary_ operations are allowed.
2. The allowed operators are +, -, \*, /, and ^.
3. Each operation is composed of two operands and an operator between them.
4. Each operation is enclosed in parentheses.
5. Operands must be either _numbers_ or parenthetically-enclosed operations.
7. Numbers are represented as finite decimals.
6. When one or both operands are themselves operations (not numbers) the
numerical value of these operands is calculated first. In other words, the
inner-most parenthetical expressions are evaluated first.

### Algorithm to Evaluate
This algorithm is known as _Dijkstra's Two Stack Algorithm_. Here are the
rules:

1. Itialize two empty stacks: one for numbers and one for operators
2. Read the string character by character, left to right. 
    - If the character is a left parenthesis "(" or a space, ignore it and move
    to the next character.
    - If the character is a numeral keep reading until the next character is
    _not_ a numeral (or decimal point). Push the _entire_ number to the number
    stack.
    - If the character is an operator, push it to the operator stack.
    - If the character is a right parenthesis ")", pop the next two numbers and
    the next operator off of their repsective stacks and apply that operator to
    the numbers. Push the result to the number stack.
3. When the end of the expression is reached, pop the last value from the number
stack. This will be the evaluation of the expression.

### Syntax Errors
Your compiler should also detect compile-time syntax errors. Fortunately, this
is pretty easy to do. There are _three_ classes of compile errors that could be
encountered:

1. Illegal character (like a letter or something)
2. Illegal expression (characters are correct but the expression doesn't follow
the syntax rules listed above.)

The first syntax error is found by adding a substep to step 2 of the algorthm
in the previous section: If the character is neither a number or an operator,
throw an `IllegalArgumentException` with a message specifying the illegal
character.

The second syntax error is found if, at the end of the evaluation, the stacks
are not both empty (except for one value in the number stack), _or_ one stack
is empty when you attempt to pop something from it. Throw an 
`IllegalArgumentException`in both of these cases. You can read more about this
exception in its corresponding [javadoc](https://docs.oracle.com/javase/7/docs/api/java/lang/IllegalArgumentException.html)

### API

Your compiler needs to have only one method, namely `public static double evaluate(String expression)`
which takes in a String formatted according to the syntax rules above and
returns its value as a double.

## Conclusion
This is the most complicated project we've had to date (actually its kind of
_two_ projects). However, if you're caught up on the class notes and have a
stack already coded you should be able to complete each of these in a week and
have the whole thing submitted by the end of the semester. Of course, you'll
need to use wisely the several hours of class time we have left, too.

Starter code is provided for both of the required classes. In my benevolence I
have also coded a `Stack` class for your enjoyment and use.