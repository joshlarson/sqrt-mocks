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

####Main

Over the next several months, Anne and Ove add more features (not shown for convenience) to their respective projects. They both eventually decide that it's time to publish their code and push out a working product.

Since their code still looks so similar, their <code>Main</code>s end up looking the same too. Go ahead and give either of their projects a try. It'll be fun!

####Changes

After building (and testing) more and more classes (not shown in this repository for convenience) that make use of the SquareRoot object, Anne starts to think that using <code>null</code> as a return value to indicate failure was a mistake. She starts to think that a more elegant way of handling that error condition would be to throw a runtime exception.

(Once again, the discussion, of whether the use of runtime exceptions is a force for good or for evil is not the intent of this forum)

So she wanders back to SquareRootTest, dusts it off, and changes <code>testNegativeOne</code> to assert that an <code>IllegalArgumentException</code> was thrown. Obviously the test fails, so she adds in the exception and it passes.

Then, before she commits, Anne does the responsible thing and runs all of the unit tests. One of them fails, one that was passing just a moment before!

"Hmmm", says Anne, puzzled. After scratching her head for a moment, she remembers that the SquareRootProcessor was based on the <code>null</code> return value, and now that that was no longer the behavior, the SquareRootProcessor was broken. Realizing that, she can change the null check to a catch clause and save her program from catastrophe.

"Phew", says Anne, relieved that this bug didn't make it out to production. "That would have been embarrassing!"

Ove, meanwhile, also starts to think that <code>null</code> was a bad idea, and also wants to throw an <code>IllegalArgumentException</code>. He makes the same adjustment as Anne to his test, asserting the exception, sees it fail, and makes the same change to his production code.

Before he commits, being the responsible TDDer that he is, Ove runs all the unit tests, and satisfied that they all pass, commits his new change.

####Bug Reports

Ove starts getting bug reports from angry customers. Complaints are pouring in, saying things like "the big red words appear and I can't make them go away". Eventually, Ove realizes that his program prints a stack trace because the <code>IllegalArgumentException</code> sneaks past his <code>SquareRootProcessor</code>.

"How foolish of me!", thinks Ove. "I updated all of the other mocked <code>SquareRoot</code>s, but I must have missed this one."

Now that he realizes this, he updates the mock in his <code>SquareRootProcessorTest</code>, and fixes the problem. He runs the unit tests, and they pass, but he no longer trusts them, so he spends a great deal of extra time and money running a full regression test to make sure that he didn't miss any other runtime exceptions. He pushes his code, but his customers don't quite trust him any more. Many of them couldn't wait for the fix and switched to his competitors, many are not willing to pay as much as before, and those who stayed are less willing to upgrade for fear of losing functionality.

Meanwhile, Anne, who fixed this problem before any customers saw it, is enjoying a stellar reputation in the Square Root Printing industry, and, with the money she rakes in from her ever-increasing market share, takes a vacation in the Bahamas, and when she gets back, she hires Ove into her company as an underpaid Junior Developer.

####Debrief

Let's start by scolding Anne T. Mokk for refusing to touch Mockito. I understand her hatred of mocking and her fear of the consequences, but a blanket refusal to do anything with Mockito is silly.

Despite the clever misleading name, her <code>StoringIODevice</code> was a mock, but with several disadvantages over doing the same thing in Mockito. Obviously, it took longer to make, since it would have been two lines of Mockito method calls. The resulting code also hides the real method signature of <code>SquareRootProcessor</code>, thereby hiding what she was actually testing. And using a custom "mock" over a Mockito mock didn't yield any real structural security.

But Anne learned that lesson from the Bahamas. Ove has a lesson to learn in the cold almost-winter of somewhere not tropical.

Ove R. Mokk pushed a bug into production. Even worse, he broke a feature that had previously worked.

You may be thinking, "Ove, you fool! Unit tests are cool, but you can't trust them with your life. You have to have some sort of backup plan: a dedicated QA team, an integration testing suite that runs on an integrtion testing server!"

All of those are good ideas, but why doesn't that apply to Anne as well? She did the same thing as Ove, and managed to avoid pushing any bugs.

Or maybe you're thinking, "Ove, you idiot! You should never use [null/unchecked exceptions]; they cause nothing but problems!"

To which I rejoinder: THAT'S NOT THE POINT! The point of using null/unchecked exceptions is to illustrate any change that the type system won't notice, but that's only one example. Maybe one module expects a sorted list and the other forgets to sort it. There are all sorts of ways to change the contract of a "unit" without changing any type-checked method signatures.

Or you might be thinking, "Ove, you clown! You should have checked ALL of your <code>SquareRoot</code> mocks to make sure that all of them behave the way that the <code>SquareRoot</code> object is supposed to! Of course you'll have problems when you miss one!"

Remember that JUnit, or whatever testing framework Ove was using, wasn't about to tell him that he broke something. So how is he going to figure out which places need changing? By searching the filesystem? How many clients of <code>SquareRoot</code> can Ove keep track of before he accidentally skips one?

Anne didn't have to keep track. She took advantage of the fact that the computer can do things like that, and the fact that the mock reliable mock of a <code>SquareRoot</code> is a <code>new SquareRoot()</code>.

Ove's fundamental problem was that his aggressive use of mocks hid from JUnit the actual structure of the system. The unit tests couldn't see the wiring, and they couldn't test the wiring, and when the wiring changed, Ove's unit tests were unable to cope.

Mocking is locking. As soon as you mock a boundary, you lock that bounday, by making it difficult-to-impossible to change. Both Ove and Anne mocked the I/O boundary, but Ove also mocked the boundary between <code>SquareRootProcessor</code> and <code>SquareRoot</code>.

Mocking a boundary that is completely stable is fine; you mock it and it never changes anyway. But if you mock your own business objects, you commit yourself to keeping those objects forever, since changing them can cause so many problems. You lock yourself prematurely into an architecture that may not be optimal.

And if you don't lock yourself into that architecture, you will break things.

Anne ran her unit tests, and when they passed, she shipped, because it meant she had no bugs.

Ove ran his unit tests, and when they passed, he still had bugs. Starts to make you wonder why Ove bothered with unit tests to begin with.
