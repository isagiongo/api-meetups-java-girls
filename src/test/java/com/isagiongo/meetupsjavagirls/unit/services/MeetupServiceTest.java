package com.isagiongo.meetupsjavagirls.unit.services;

import com.isagiongo.meetupsjavagirls.exceptions.MeetupEditionAlreadyExists;
import com.isagiongo.meetupsjavagirls.exceptions.MeetupNotFoundException;
import com.isagiongo.meetupsjavagirls.models.Meetup;
import com.isagiongo.meetupsjavagirls.models.dtos.MeetupRequestDTO;
import com.isagiongo.meetupsjavagirls.repository.MeetupRepository;
import com.isagiongo.meetupsjavagirls.repository.TalkRepository;
import com.isagiongo.meetupsjavagirls.services.MeetupService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MeetupServiceTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private MeetupService meetupService;

    @Mock
    private MeetupRepository meetupRepository;

    @Mock
    private TalkRepository talkRepository;

    private MeetupRequestDTO meetupDTO;

    @Before
    public void setUp() {
        meetupDTO = MeetupBuilder.criaMeetupComTalks();
    }

    @Test
    public void deveChamarSalvarSeEdicaoNaoexistir () {
        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.empty());
        meetupService.create(meetupDTO);

        verify(meetupRepository).save(any());
    }

    @Test
    public void deveRetornarExcecaoAoSalvarEdicaoDeMeetupJaExistitente() {
        exception.expect(MeetupEditionAlreadyExists.class);
        exception.expectMessage("Essa edição do meetup já está cadastrada.");

        Meetup meetup = new Meetup(meetupDTO);

        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.of(meetup));

        meetupService.create(meetupDTO);
    }

    @Test
    public void deveRetornarExcecaoAoBuscarMeetupNaoExistentePorId() {
        exception.expect(MeetupNotFoundException.class);
        exception.expectMessage("Meetup não encontrado.");

        meetupService.findById("1223dsds23");
    }

    @Test
    public void deveRetornarMeetupExistenteAoBuscarPorId() {
        Meetup meetup = new Meetup(meetupDTO);

        when(meetupRepository.findById("1223dsds23")).thenReturn(Optional.of(meetup));

        Meetup meetupRetornado = meetupService.findById("1223dsds23");

        assertNotNull(meetupRetornado);
    }

    @Test
    public void deveRetornarExcecaoAoBuscarMeetupNaoExistentePorEdicao() {
        exception.expect(MeetupNotFoundException.class);
        exception.expectMessage("Meetup não encontrado.");

        meetupService.findByEdicao(8);
    }

    @Test
    public void deveRetornarMeetupExistenteAoBuscarPorEdicao() {
        Meetup meetup = new Meetup(meetupDTO);

        when(meetupRepository.findByEdicao(1)).thenReturn(Optional.of(meetup));

        Meetup meetupRetornado = meetupService.findByEdicao(1);

        assertNotNull(meetupRetornado);
    }
}
