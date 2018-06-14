package test.project.vkapi.di.modules;


import dagger.Binds;
import dagger.Module;
import test.project.vkapi.core.db.AppRepository;
import test.project.vkapi.core.db.IAppRepository;

@Module
public abstract class RepositoryModule {

    @Binds
    public abstract IAppRepository bindAppRepository(AppRepository appRepository);

}
