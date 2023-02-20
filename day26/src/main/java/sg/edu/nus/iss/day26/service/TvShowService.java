package sg.edu.nus.iss.day26.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.day26.model.TvShow;
import sg.edu.nus.iss.day26.repository.TvShowsRepo;

@Service
public class TvShowService {
    
    @Autowired
    private TvShowsRepo tvShowRepo;

    public List<TvShow> findAllTvShowsByLanguage(String lang){
        return tvShowRepo.findTvShowsByLanguage(lang).stream()
                    .map(v -> TvShow.create(v))
                    .toList();
        
    }

    public List<String> listAllTypes(){
        return tvShowRepo.listAllTypes();
    }

    public List<TvShow> findAllTvShowsByType(String type){
        return tvShowRepo.findTvShowsByType(type).stream()
                    // some types in source are integer and 0; need to fix to conform with 'Double' type
                    // .filter(v -> {
                    //     try {
                    //         TvShow.createSummary(v);
                    //         return true;
                    //     } catch (Exception ex) {return false;}
                    // })
                    .map(v -> TvShow.createSummary(v))
                    .toList();
    }

    public List<TvShow> findTvShowDetail(int id){
        return tvShowRepo.findTvShowDetail(id).stream()
                    // some types in source are integer and 0; need to fix to conform with 'Double' type
                    // .filter(v -> {
                    //     try {
                    //         TvShow.createSummary(v);
                    //         return true;
                    //     } catch (Exception ex) {return false;}
                    // })
                    .map(v -> TvShow.createDetail(v))
                    .toList();
    }
}

