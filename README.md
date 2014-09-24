sqrt-mocks
==========

This repository tells the story of two intrepid young programmers, Ove R. Mokk, and Anne T. Mokk. They both follow TDD to the letter, but their tests end up looking completely different because they have very different philosophies about testing.

Ove believes that in a unit test, a unit, or class, must be tested in isolation. His rule is that, if you run <code>ThingTest.java</code> with a code coverage tool, it should show 100% coverage for <code>Thing.java</code>, and 0% coverage for everything else. He accomplishes this with aggressive use of the Mockito framework.

Anne, on the other hand, hates mocking. She prefers every unit test to test as much of her system as possible and refuses to mock anything. She does not even include Mockito among her dependencies.

And before we begin, let's not spend time asking "But is Anne really writing UNIT tests? Can it be a UNIT test if her test exercises more than one class?" Such questions are questions for dictionaries and linguists, not for programmers, who prefer to see what benefit Anne's tests can give her, regardless of what they are called.
