package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.helpers.Utils;
import org.bszabat.mgrbackend.exception.BadRequest;
import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.helpers.URLHelper;
import org.bszabat.mgrbackend.model.AlbumDto;
import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.model.PhotoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SyncApiService {

    private RestTemplate restTemplate = new RestTemplate();

    private Utils utils;

    @Autowired
    public SyncApiService(Utils utils) {
        this.utils = utils;
    }

    public String getAllPhotosTime(int quantity) {
        if (!"5000,10000,40000,120000".contains(String.valueOf(quantity))) {
            throw new BadRequest("Parameter quantity should be one of {5000, 10000, 40000, 120000}");
        }
        return utils.measureTime(quantity, this::getAllPhotos);
    }

    private List<Photo> getAllPhotos(int quantity) {
        ResponseEntity<PhotoDto[]> responsePhotos = restTemplate.getForEntity(URLHelper.PHOTOS + quantity, PhotoDto[].class);
        if (responsePhotos.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from: " + URLHelper.PHOTOS + quantity);
        }

        ResponseEntity<AlbumDto[]> responseAlbums = restTemplate.getForEntity(URLHelper.ALBUMS, AlbumDto[].class);
        if (responseAlbums.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from: " + URLHelper.ALBUMS);
        }

        List<AlbumDto> albums = Arrays.asList(responseAlbums.getBody());

        return Arrays.stream(responsePhotos.getBody())
                .map(p -> new Photo(p.getId(),
                                    p.getAlbumId(),
                                    p.getTitle(),
                                    p.getUrl(),
                                    p.getThumbnailUrl(),
                                    albums.stream()
                                        .filter(a -> a.getAlbumId() == getAlbumIdAndReduceWithinRange(p.getAlbumId(), 10))
                                        .map(AlbumDto::getDescription)
                                        .collect(Collectors.joining())))
                .collect(Collectors.toList());
    }

    private int getAlbumIdAndReduceWithinRange(Integer param, int range) {
        if (param > 99) {
            return 1;
        }
        else if (param <= range) {
            return param;
        }
        String paramStr = param.toString();
        return Integer.parseInt(paramStr.substring(paramStr.length() - 2, paramStr.length() - 1));
    }
}
