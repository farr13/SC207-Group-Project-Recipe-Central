package users.service.make_cookbook;

public interface MakeCookbookOutputBoundary {
    void prepareSuccessView(MakeCookbookOutputData response);

    void prepareFailView(String error);
}
