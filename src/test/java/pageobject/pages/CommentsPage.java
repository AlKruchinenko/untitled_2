package pageobject.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentsPage {
    private final By COMMENTS_PAGE = By.xpath("//*[contains(@class, 'center article-comments-heading')]");
    private pageobject.pages.BaseFunc baseFunc;

    public CommentsPage(pageobject.pages.BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    public int getComments() {
        LOGGER.info("Getting comments count");
        String commentsToParse = baseFunc.getText(COMMENTS_PAGE);
        return Integer.parseInt(commentsToParse);


    }
}
