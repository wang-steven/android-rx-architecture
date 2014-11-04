package tw.steven.project.models;

import tw.steven.project.models.pojo.Author;
import tw.steven.project.models.pojo.Book;
import tw.steven.project.models.pojo.SearchEngineResults;
import tw.steven.project.network.MyProjectApi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

public class BiographiesModel {

    private static BiographiesModel instance;

    public static BiographiesModel getInstance() {
        if (instance == null) {
            instance = new BiographiesModel();
        }
        return instance;
    }

    private BiographiesModel() {}

    public Observable<List<Book>> getBiographyBooks(String query) {
        return MyProjectApi.getInstance().getSearchEngineResults(query).map(convertSearchResultsToBooks(5));
    }

    private Func1<SearchEngineResults, List<Book>> convertSearchResultsToBooks(final int maxAmount) {
        return new Func1<SearchEngineResults, List<Book>>() {

            @Override
            public List<Book> call(SearchEngineResults results) {
                if (results == null || results.RelatedTopics == null) {
                    return new ArrayList<Book>();
                }

                ArrayList<Book> listBooks = new ArrayList<Book>(maxAmount);
                for (int i = 0, s = results.RelatedTopics.size(); i < maxAmount && i < s; i++) {
                    SearchEngineResults.Topic topic = results.RelatedTopics.get(i);
                    Book book = new Book(topic.firstURL);
                    book.title = "Biography of " + topic.text;
                    listBooks.add(book);
                }
                return listBooks;
            }
        };
    }

    public Observable<Author> getAuthor(String bookId) {
        Author author = new Author();
        author.name = "Smith Johnson";
        return Observable.just(author).delay(2, TimeUnit.SECONDS);
    }

    public Observable<Integer> getBookPrice(String bookId) {
        return Observable.interval(1, TimeUnit.SECONDS)
            .map(new Func1<Long, Integer>() {

                @Override
                public Integer call(Long aLong) {
                    return Math.round(100 + aLong * aLong);
                }
            }).take(20);
    }
}
