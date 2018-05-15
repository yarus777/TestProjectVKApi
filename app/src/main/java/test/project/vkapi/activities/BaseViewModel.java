package test.project.vkapi.activities;

public abstract class BaseViewModel<T extends BaseActivity> {
    private T view;

    public void setView(T view) {
        this.view = view;
    }

    protected T getView() {
        return view;
    }
}
