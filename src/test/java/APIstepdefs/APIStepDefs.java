package APIstepdefs;

import baseurls.BaseURL;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class APIStepDefs extends BaseURL {
    private final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    @Given("User validates post count for specific users.")
    public void user_validates_post_count_for_specific_users() {
        Object[][] testCases = {
                {5, 10},
                {7, 10},
                {9, 10}
        };

        for (Object[] testCase : testCases) {
            int userId = (int) testCase[0];
            int expectedPostCount = (int) testCase[1];

            Response response = spec.get();

            Assert.assertEquals(String.valueOf(response.statusCode()), 200, "API call failed.");

            List<Map<String, Object>> posts = response.jsonPath().getList("");
            long actualPostCount = posts.stream()
                    .filter(post -> (int) post.get("userId") == userId)
                    .count();

            Assert.assertEquals(String.valueOf(actualPostCount), expectedPostCount,
                    "Post count doesn't match for user " + userId);
        }

    }
    @When("User provides each blog post has a unique ID")
    public void user_provides_each_blog_post_has_a_unique_id() {
        Response response = spec.get();

        Assert.assertEquals(String.valueOf(response.statusCode()), 200, "API call failed.");

        List<Integer> ids = response.jsonPath().getList("id");
        HashSet<Integer> uniqueIds = new HashSet<>(ids);
        Assert.assertEquals(String.valueOf(uniqueIds.size()), ids.size(), "All posts don't have unique IDs.");
    }
}

