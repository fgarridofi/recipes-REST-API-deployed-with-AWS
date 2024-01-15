package validators;

import play.data.validation.Constraints;
import play.libs.F;

public class DificultadValidator extends Constraints.Validator<Integer>{
    @Override
    public boolean isValid(Integer object) {
        return object >= 1 && object <= 5;
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String,Object[]>("invalid-difficulty", new Object[]{});
    }
}
