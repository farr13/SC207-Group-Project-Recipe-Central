package app;

import backend.service.back_to_menu.BackToMenuController;
import backend.service.back_to_menu.BackToMenuInteractor;
import backend.service.back_to_menu.BackToMenuPresenter;
import backend.service.delete_recipe.*;
import backend.service.see_list_cookbooks.SeeListCookbooksController;
import backend.service.see_list_cookbooks.SeeListCookbooksDAI;
import backend.service.see_list_cookbooks.SeeListCookbooksInteractor;
import backend.service.see_list_cookbooks.SeeListCookbooksPresenter;
import view.usecase_views.OpenCookbookView;
import view.view_managers.ViewManagerModel;
import view.view_models.CookbookListViewModel;
import view.view_models.MainMenuViewModel;
import view.view_models.OpenCookbookViewModel;

public class OpenCookbookViewUseCaseFactory {
    private OpenCookbookViewUseCaseFactory(){}

    public static OpenCookbookView create(ViewManagerModel viewManagerModel, OpenCookbookViewModel openCookbookViewModel,
                                          CookbookListViewModel cookbookListViewModel, MainMenuViewModel mainMenuViewModel,
                                          SeeListCookbooksDAI viewCookbookDAO, DeleteRecipeDAI deleteRecipeDAO,ViewRecipeDAI viewRecipeDAO){
        DeleteRecipeController deleteRecipeController = OpenCookbookViewUseCaseFactory.createDeleteRecipeUseCase(viewManagerModel,
                openCookbookViewModel, deleteRecipeDAO, viewRecipeDAO);
        SeeListCookbooksController seeListCookbooksController = OpenCookbookViewUseCaseFactory.createSeeListCookbooksUseCase(viewManagerModel,
                cookbookListViewModel, viewCookbookDAO);
        BackToMenuController backToMenuController = OpenCookbookViewUseCaseFactory.createBackToMenuUseCase(viewManagerModel,mainMenuViewModel);
        return new OpenCookbookView(openCookbookViewModel, seeListCookbooksController, deleteRecipeController, backToMenuController);
    }

    private static DeleteRecipeController createDeleteRecipeUseCase(ViewManagerModel viewManagerModel,
                                                                    OpenCookbookViewModel openCookbookViewModel,
                                                                    DeleteRecipeDAI deleteRecipeDAO,
                                                                    ViewRecipeDAI viewRecipeDAO){
        DeleteRecipePresenter deleteCookbookPresenter = new DeleteRecipePresenter(viewManagerModel, openCookbookViewModel);
        DeleteRecipeInteractor deleteRecipeInteractor = new DeleteRecipeInteractor(deleteRecipeDAO, viewRecipeDAO, deleteCookbookPresenter);
        return new DeleteRecipeController(deleteRecipeInteractor);
    }
    private static SeeListCookbooksController createSeeListCookbooksUseCase(ViewManagerModel viewManagerModel,
                                                                            CookbookListViewModel cookbookListViewModel,
                                                                            SeeListCookbooksDAI viewCookbookDAO){
        SeeListCookbooksPresenter seeListCookbooksPresenter = new SeeListCookbooksPresenter(viewManagerModel, cookbookListViewModel);
        SeeListCookbooksInteractor seeListCookbooksInteractor = new SeeListCookbooksInteractor(viewCookbookDAO, seeListCookbooksPresenter);
        return new SeeListCookbooksController(seeListCookbooksInteractor);
    }
    private static BackToMenuController createBackToMenuUseCase(ViewManagerModel viewManagerModel, MainMenuViewModel mainMenuViewModel){
        BackToMenuPresenter backToMenuPresenter = new BackToMenuPresenter(viewManagerModel, mainMenuViewModel);
        BackToMenuInteractor backToMenuInteractor = new BackToMenuInteractor(backToMenuPresenter);
        return new BackToMenuController(backToMenuInteractor);
    }
}
