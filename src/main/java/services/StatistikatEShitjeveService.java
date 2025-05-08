package services;

import CustomExceptions.InvalidInputException;
import CustomExceptions.OperationFailedException;
import CustomExceptions.ResourceNotFoundException;
import CustomExceptions.ValidationException;
import models.dto.StatistikatEShitjeve.CreateStatistikatEShitjeveDto;
import models.dto.StatistikatEShitjeve.StatistikatEShitjeve;
import models.dto.StatistikatEShitjeve.UpdateStatistikatEShitjeveDto;
import repository.StatistikatEShitjeveRepository;

import java.util.List;

public class StatistikatEShitjeveService {
    private StatistikatEShitjeveRepository statistikatEShitjeveRepository;
            public StatistikatEShitjeveService(){
                 this.statistikatEShitjeveRepository = new StatistikatEShitjeveRepository();
            }
            public List<StatistikatEShitjeve> getAll(){
                 return statistikatEShitjeveRepository.getAll();
            }
            public StatistikatEShitjeve getById(int id) throws Exception{
                if(id<0){
                    throw new InvalidInputException("Id e statistikave duhet te jete pozitive! ");
                }
                StatistikatEShitjeve statistikat = this.statistikatEShitjeveRepository.getById(id);
                if(statistikat == null){
                    throw new ResourceNotFoundException("Statistika me id: " + id + "nuk ekziston! ");
                }
                return statistikat;
            }
            public StatistikatEShitjeve create(CreateStatistikatEShitjeveDto createStatistikatEShitjeve) throws  Exception{
                validateCreateDto(createStatistikatEShitjeve);
                return statistikatEShitjeveRepository.create(createStatistikatEShitjeve);
            }
            private void validateCreateDto(CreateStatistikatEShitjeveDto createStatistikatEShitjeveDto) throws Exception{
                if(isNullOrShort(createStatistikatEShitjeveDto.getMuaji(), 3)){
                    throw new InvalidInputException("Muaji duhet te kete te pakten 3 karaktere.");
                }
                if(isNull(createStatistikatEShitjeveDto.getFitimi())){
                    throw new InvalidInputException("Fitimi duhet te kete vlere pozitive. ");
                }
                if(isNull(createStatistikatEShitjeveDto.getShpenzimet())){
                    throw new InvalidInputException("Shpenzimet duhet te kene vlere pozitive.");
                }
                if(isNull(createStatistikatEShitjeveDto.getTotali_shitjeve())){
                    throw new InvalidInputException("Totali i shitjeve duhet te kete vlere pozitive. ");
                }
            }
            private boolean isNullOrShort(String s, int minLength ){
                return s == null || s.trim().length() < minLength;
            }
            private boolean isNull(Double d){
                return d == null || d <= 0;
            }
            public StatistikatEShitjeve update(UpdateStatistikatEShitjeveDto updateStatistikatEShitjeveDto) throws Exception{
                if(updateStatistikatEShitjeveDto.getId() <= 0){
                    throw new ValidationException("Id e statistikave eshte e pavlefshme!");
                }
                StatistikatEShitjeve statistikatEShitjeve = statistikatEShitjeveRepository.getById(updateStatistikatEShitjeveDto.getId());
                if(statistikatEShitjeve == null){
                    throw new ResourceNotFoundException("Statistikat me ID " + updateStatistikatEShitjeveDto.getId() + " nuk ekziston.");
                }
                boolean hasChanges = false;

                if(updateStatistikatEShitjeveDto.getFitimi() != null){
                    if(updateStatistikatEShitjeveDto.getFitimi() < 0){
                        throw new InvalidInputException("Fitimi duhet te kete vlere pozitive.");
                    }
                    hasChanges = true;
                }
                if(updateStatistikatEShitjeveDto.getShpenzimet() != null){
                    if(updateStatistikatEShitjeveDto.getShpenzimet() < 0){
                        throw new InvalidInputException("Shpenzimet duhet te kene vlere pozitive.");
                    }
                    hasChanges = true;
                }
                if(updateStatistikatEShitjeveDto.getTotali_shitjeve() != null){
                    if(updateStatistikatEShitjeveDto.getTotali_shitjeve() < 0){
                        throw new InvalidInputException("Totali shitjeve duhet te kete vlere pozitive.");
                    }
                    hasChanges = true;
                }

                if(!hasChanges){
                    throw new ValidationException("Duhet te perditesohet te pakten nje fushe.");
                }
                StatistikatEShitjeve updated = statistikatEShitjeveRepository.update(updateStatistikatEShitjeveDto);
                if(updated == null){
                    throw new OperationFailedException("Update-i deshtoi. Statistikat e shitjeve nuk u perditesuan.");
                }
                return updated;
            }
            public boolean delete(int id) throws Exception{
                if(id <= 0){
                    throw new ValidationException("Id e statistikave eshte e pavlefshme. Duhet te jete > 0.");
                }
                StatistikatEShitjeve statistikatEShitjeve = statistikatEShitjeveRepository.getById(id);
                if(statistikatEShitjeve == null){
                    throw new ResourceNotFoundException("Statistikat nuk ekzistojne!");
                }
                return statistikatEShitjeveRepository.delete(id);
            }
            public List<StatistikatEShitjeve> KerkoStatistikat(String muaji){
                return statistikatEShitjeveRepository.searchhByMonth(muaji);
            }
}
