package pageobject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pageobject.pages.ArticlePage;
import pageobject.pages.CommentsPage;
import pageobject.pages.HomePage;

public class Homework {
    private final Logger LOGGER = LogManager.getLogger(this.getClass());
    private final int ARTICLE_ID = 0;
    private pageobject.pages.BaseFunc baseFunc;

    @Test
    public void HomeWork() {
        LOGGER.info("This test is checking titles and comments count on home/article/comments pages");

        baseFunc = new pageobject.pages.BaseFunc();
        baseFunc.openPage("tvnet.lv");

        HomePage homePage = new HomePage(baseFunc);
        homePage.acceptCookies();
        homePage.getArticleById(0);

        LOGGER.info("Getting article title and comments count");

        String homePageTitle = homePage.getTitle(ARTICLE_ID);
        int homePageCommentsCount = homePage.getCommentsCount(ARTICLE_ID);


        LOGGER.info("Opening article");

        ArticlePage articlePage = homePage.openArticle(ARTICLE_ID);

        String articlePageTitle = articlePage.getTitle();
        int articlePageCommentsCount = articlePage.getCommentsCount();

        Assertions.assertEquals(homePageTitle, articlePageTitle + " (" + articlePageCommentsCount + ")", "Wrong title");
        Assertions.assertEquals(homePageCommentsCount, articlePageCommentsCount, "Wrong comments count");

        CommentsPage commentsPage = new CommentsPage(baseFunc);
        int commentsPageComments = commentsPage.getComments();

        LOGGER.info("Opening comments page");

        Assertions.assertEquals(articlePageCommentsCount, commentsPage, "Wrong comments page");
        LOGGER.info("close Browser");
    }

    @AfterEach
    public void closeBrowser() {
        baseFunc.closeBrowser();
    }
}

