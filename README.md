sqrt-mocks
==========

This repository tells the story of two intrepid young programmers, Ove R. Mokk, and Anne T. Mokk. They both follow TDD to the letter, but their tests end up looking completely different because they have very different philosophies about testing.

Ove believes that in a unit test, a unit, or class, must be tested in isolation. His rule is that, if you run <code>ThingTest.java</code> with a code coverage tool, it should show 100% coverage for <code>Thing.java</code>, and 0% coverage for everything else. He accomplishes this with aggressive use of the Mockito framework.

Anne, on the other hand, hates mocking. She prefers every unit test to test as much of her system as possible and refuses to mock anything. She does not even include Mockito among her dependencies.

And before we begin, let's not spend time asking "But is Anne really writing UNIT tests? Can it be a UNIT test if her test exercises more than one class?" Such questions are questions for dictionaries and linguists, not for programmers, who prefer to see what benefit Anne's tests can give her, regardless of what they are called.

####Setup

Ove and Anne both decide to create a simple project that does fun things with square roots. They both decide to do their projects in Java, using Maven as their build systems. Their Maven scripts look identical except for one small difference: Ove includes mockito as a test dependency, while Anne does not.

Feel free to import their work into your favorite IDE. Anne called her project "sqrt-without-mocks", while Ove named his "sqrt-with-mocks". It should be straightforward to tell which project will make use of Mockito, and which will not.

####Square Roots

Ove wants a square root generator. It should take in a Double object and return a Double object containing the square root of the input. If the input is in any way invalid, it should return <code>null</code>.

Being the diligent student of TDD that he is, Ove writes four unit tests, alternating between modifying code and modifying tests. By the time he is finished, he knows that his square root calculator correctly computes the square root, and correctly errors with <code>null</code>.

(Please note that this is not the time to comment on whether <code>null</code> is a wise choice to signal an error condition. This is the decision that Ove has made, and the merits of that decision do not bear on the lessons of this story.)

The SquareRoot class that Ove has just created is completely self-sufficient, and his tests do not require the use of Mockito. Thus, Anne has no problem with the code he just wrote, which is why her initial code looks exactly the same.

####Processing

Ove has decided that his "fun with square roots" will be to have the user enter a number, and have his program output the square root of that number. It will be great fun. Obviously, if the user enters something incorrectly, they should get a warning message, rather than having the whole program crash, but that's easy enough to ensure.

Again following the precepts of TDD, Ove writes one test, then two, then three, each time adjusting the code to make the tests pass, each time dutifully mocking out absolutely everything other than the Transmitter object he's testing. He's mocked the <code>SquareRoot</code> object and the <code>IODevice</code> obejct, but when he writes <code>main</code>, he will simply plug in the actual <code>SquareRoot</code> and the concrete <code>ConsoleIODevice</code>.

Anne is hoping to accomplish the same thing, but she refuses to use Mockito. For the <code>SquareRoot</code> dependency, that's easy; she just puts in a real <code>new SquareRoot()</code>, but the <code>IODevice</code> is a bit trickier. She could plug in a <code>new ConsoleIODevice</code>, but she can't assert any actual calls on that object.

No, in this case, Anne needs to create a special type of <code>IODevice</code> that holds a sample read value and stores whatever was written to it. Then she can reach in and assert that the right value was written.

Like Ove, Anne writes her tests and her code in parallel, running the tests frequently to make sure that everything works. In fact, though her tests look very different from Ove's, the production code they've produced is identical.
