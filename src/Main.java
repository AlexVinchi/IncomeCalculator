import java.util.Scanner;

public class Main {
    private static int minIncome = 200000;
    private static int maxIncome = 900000;

    private static int officeRentCharge = 140000;
    private static int telephonyCharge = 12000;
    private static int internetAccessCharge = 7200;

    private static int assistantSalary = 45000;
    private static int financeManagerSalary = 90000;

    private static double mainTaxPercent = 0.24;
    private static double managerPercent = 0.15;

    private static double minInvestmentsAmount = 100000;

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите сумму доходов компании за месяц " +
                "(от 200 до 900 тысяч долларов): ");
            int income = (new Scanner(System.in)).nextInt();

            if (!checkIncomeRange(income)) {
                continue;
            }

            double managerSalary = income * managerPercent;
            double pureIncome = income - managerSalary -
                calculateFixedCharges();
            double taxAmount = Math.round(mainTaxPercent * pureIncome);
            double pureIncomeAfterTax = pureIncome - taxAmount;

            boolean canMakeInvestments = pureIncomeAfterTax >=
                minInvestmentsAmount;
            boolean bep = false;
            boolean minInvestMarge = false;
            double mIncome = 0;

            var pointBreakeven = Math.round((calculateFixedCharges() / (1 - (1 * managerPercent))) * 1);
            System.out.println("==========================================================");
            System.out.println("Базовые расходы: " + calculateFixedCharges());
            System.out.println("Зарплата менеджера: " + Math.round(managerSalary));
            System.out.println("Общая сумма налогов: " + (taxAmount > 0 ? taxAmount : 0));

            System.out.println("При всех расходах до налогов, точка безубыточности составляет: " +pointBreakeven+ " долларов.");

            for(var inc = pointBreakeven; inc <= 900000; inc++) {

                double managerSalary1 = inc * managerPercent;
                double pureInc = inc - managerSalary1 - calculateFixedCharges();
                double taxAmount1 = mainTaxPercent * pureInc;
                double pureIncomeAfterTax1 = pureInc - taxAmount1;
                if (pureIncomeAfterTax1 >= minInvestmentsAmount){
                    System.out.println("Минимальный доход компании в размере " +inc+ " долларов позволит инвестировать.");
                           mIncome = Math.round(inc);
                    break;
                }
            }

                if (canMakeInvestments){
                    System.out.println("Компания может инвестировать, чистая прибыль состовляет: " + Math.round(pureIncomeAfterTax) + " долларов. \n");
                }else{
                    System.out.println("Компания сейчас не может инвестировать: нехватака дохода в размере: " + Math.round(mIncome-income) + " долларов.");
                };
            if (pureIncome < 0) {
                System.out.println("Бюджет в минусе! Нужно срочно зарабатывать!");
            }
        }
    }

    private static boolean checkIncomeRange(int income) {
        if (income < minIncome) {
            System.out.println("Доход меньше нижней границы");
            return false;
        }
        if (income > maxIncome) {
            System.out.println("Доход выше верхней границы");
            return false;
        }
        return true;
    }

    private static int calculateFixedCharges() {
        return officeRentCharge +
            telephonyCharge +
            internetAccessCharge +
            assistantSalary +
            financeManagerSalary;
    }
}
