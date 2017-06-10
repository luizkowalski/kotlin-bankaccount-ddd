package net.luizkowalski.accountsservice.presentation.models

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.Range

@Data
@AllArgsConstructor
@NoArgsConstructor
class TransactionsParam {

    @NotBlank(message = "please inform IBAN")
    var iban: String = ""

    @Range(min = 1, message = "value should be bigger than 0")
    var amount: Long = 0

    @NotBlank(message = "please inform recipient's IBAN")
    var recipient: String = ""
}