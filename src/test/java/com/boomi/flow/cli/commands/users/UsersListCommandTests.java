package com.boomi.flow.cli.commands.users;

import com.boomi.flow.cli.client.Callback;
import com.boomi.flow.cli.client.FlowClient;
import com.boomi.flow.cli.client.User;
import com.boomi.flow.cli.commands.CommandTestBase;
import com.boomi.flow.cli.configuration.Configuration;
import com.boomi.flow.cli.configuration.Runtime;
import com.boomi.flow.cli.options.CommonOptions;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class UsersListCommandTests extends CommandTestBase {

    @Mock
    private FlowClient flowClient;

    @Test
    public void testRunCommand() {
        Runtime runtime = new Runtime();
        runtime.setHost("https://example.com");
        runtime.setName("example");
        runtime.setUser(new Runtime.User("12345"));

        Configuration configuration = new Configuration();
        configuration.getRuntimes().add(runtime);

        // Mock the API response for listing users
        doAnswer(invocationOnMock -> {
            User user = new User();
            user.setEmail("fake@example.com");
            user.setFirstName("Fake");
            user.setId(UUID.fromString("0693665b-f85c-4a44-845e-96080aeba8b1"));
            user.setLastName("Name");

            Callback callback = invocationOnMock.getArgument(1, Callback.class);
            callback.onResponse(Lists.newArrayList(user));

            return null;
        }).when(flowClient).listUsers(eq("12345"), any());

        CommonOptions commonOptions = new CommonOptions();
        commonOptions.setRuntime("example");
        commonOptions.setTenant(UUID.fromString("c10c3d9d-4f60-45f5-b2c1-eae4076135da"));

        // Run the command
        UsersListCommand usersListCommand = new UsersListCommand(flowClient, configuration);
        usersListCommand.setCommonOptions(commonOptions);
        usersListCommand.run();

        assertThat(outContent.toString(), containsString("Loading users..."));

        assertThat(outContent.toString(), containsString("ID"));
        assertThat(outContent.toString(), containsString("Name"));
        assertThat(outContent.toString(), containsString("Email"));

        assertThat(outContent.toString(), containsString("0693665b-f85c-4a44-845e-96080aeba8b1"));
        assertThat(outContent.toString(), containsString("Fake Name"));
        assertThat(outContent.toString(), containsString("fake@example.com"));
    }
}
