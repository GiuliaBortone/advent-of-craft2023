package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

public class Pipeline {
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        if (!project.hasTests()) {
            log.info("No tests");
            deploymentMessageHandler(project);
            return;
        }

        whenProjectHasTests(project);
    }

    private boolean runTestsWasNotSuccessful(Project project) {
        return !"success".equals(project.runTests());
    }

    private void whenProjectHasTests(Project project) {
        if (runTestsWasNotSuccessful(project)) {
            log.error("Tests failed");
            sendEmailWithMessage("Tests failed");
            return;
        }
        String testPassingMessage = "Tests passed";
        log.info(testPassingMessage);
        deploymentMessageHandler(project);
    }

    private void deploymentMessageHandler(Project project) {
        if (deployWasNotSuccessful(project)) {
            log.error("Deployment failed");
            sendEmailWithMessage("Deployment failed");
            return;
        }
        log.info("Deployment successful");
        sendEmailWithMessage("Deployment completed successfully");
    }

    private static boolean deployWasNotSuccessful(Project project) {
        return !"success".equals(project.deploy());
    }

    private void sendEmailWithMessage(String message) {
        if (sendEmailIsDisabled()) {
            log.info("Email disabled");
            return;
        }
        log.info("Sending email");
        emailer.send(message);
    }

    private boolean sendEmailIsDisabled() {
        return !config.sendEmailSummary();
    }
}
