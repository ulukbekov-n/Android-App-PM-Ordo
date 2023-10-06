import com.example.pmordo.domain.base.Mapper
import com.example.pmordo.domain.models.SellerSignUpDomain
import com.example.pmordo.presentation.models.SellerSignUp

class SellerSignUpToSellerSignUpDomainMapper : Mapper<SellerSignUp, SellerSignUpDomain> {
    override fun map(from: SellerSignUp): SellerSignUpDomain {
        return SellerSignUpDomain(
            email = from.email,
            username = from.username,
            password = from.password,
            INN = from.INN,
            companyName = from.companyName,
            companyPosition = from.companyPosition,
            role = from.role

        )
    }
}
