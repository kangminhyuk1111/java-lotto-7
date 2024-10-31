package lotto.domain.store;

import lotto.domain.entity.Lotto;
import lotto.domain.entity.Lottos;
import lotto.domain.exception.PayException;
import lotto.domain.exception.PayExceptionMessage;
import lotto.domain.generate.LottoNumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class LottoStore {

    public static final int LOTTO_PRICE = 1000;
    public static final int ZERO = 0;

    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoStore(final LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public Lottos issueLottos(final int pay) {
        validatePayment(pay);

        final int lottoCount = pay / LOTTO_PRICE;

        final List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(generateLotto());
        }

        return new Lottos(lottos);
    }

    private Lotto generateLotto() {
        return lottoNumberGenerator.generateLotto();
    }

    private void validatePayment(final int pay) {
        if (isPaymentValid(pay)) {
            throw new PayException(PayExceptionMessage.PAY_BOUNDED_EXCEPTION);
        }

        if (isDivisibleByPayment(pay)) {
            throw new PayException(PayExceptionMessage.PAY_DIVISIBLE_EXCEPTION);
        }
    }

    private boolean isPaymentValid(final int pay) {
        return pay < LOTTO_PRICE;
    }

    private boolean isDivisibleByPayment(final int pay) {
        return pay % LOTTO_PRICE != ZERO;
    }
}