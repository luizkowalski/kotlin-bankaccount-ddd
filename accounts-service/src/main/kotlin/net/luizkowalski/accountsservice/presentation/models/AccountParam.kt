package net.luizkowalski.accountsservice.presentation.models

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotBlank
import org.hibernate.validator.constraints.Range

@Data
@NoArgsConstructor
@AllArgsConstructor
class AccountParam() {

    @NotBlank(message = "please inform a name")
    var name: String = ""

    @Email(message = "It is not a valid e-mail")
    var email: String = ""

    @NotBlank(message = "please inform a passport number")
    var passportNumber: String = ""

    @Range(min = 0, max = 1, message = "account type out of range. Allowed values: 0-1")
    var accountType: Int = 0
}
