package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.mgrtests.URLHelper;
import org.bszabat.mgrbackend.model.AlbumDto;
import org.bszabat.mgrbackend.model.Photo;
import org.bszabat.mgrbackend.model.PhotoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IOBlockingService {

    private RestTemplate restTemplate = new RestTemplate();

    public List<Photo> getAllPhotos() {
        ResponseEntity<PhotoDto[]> responsePhotos = restTemplate.getForEntity(URLHelper.PHOTOS_TEST_5000, PhotoDto[].class);
        if (responsePhotos.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from: " + URLHelper.PHOTOS_TEST_40000);
        }

        ResponseEntity<AlbumDto[]> responseAlbums = restTemplate.getForEntity(URLHelper.ALBUM_TEST_10, AlbumDto[].class);
        if (responseAlbums.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from: " + URLHelper.ALBUM_TEST_10);
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
