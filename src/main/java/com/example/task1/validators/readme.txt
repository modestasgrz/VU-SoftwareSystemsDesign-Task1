Unit testai buvo pakankamai geri, nebuvo sunku dirbti, tačiau galima būtų patobulinti kelis dalykus:
- phoneValidatorAllNumbersTest galima būtų pridėti numerį, prasidedantį su +
- Telefono changePrefix metode būtų galima pridėti country code parametrą, kad nereikėtų tikrintį pagal pirmus skaitmenis, kokios šalies numeris gautas.
- emailValidatorTopLevelDomainTest galėtų būti daugiau neteisingų TLD.

SVARBU: -pagal emailValidatorTopLevelDomainTest, email@mail.aaccc turi netinkamą TLD, nors šis TLD teoriškai yra leidžiamas.
        -phoneValidatorLengthByCountryTest yra padaryta klaida rašant numerį ("+3760356633", o ne "+37060356633"),
