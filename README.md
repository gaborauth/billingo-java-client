# Java client for billingo.hu API

Usage examples from [MainClass.java](https://github.com/gaborauth/billingo-java-client/blob/master/src/main/java/hu/billingo/MainClass.java):

```java
    public static final void main(final String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length < 2) {
            System.out.println("Usage: java -jar billingo-java-client.jar <publicKey> <privateKey>");
            return;
        }

        final String publicKey = args[0];
        final String privateKey = args[1];

        final BillingoClient billingoClient = BillingoClient.builder()
                .publicKey(publicKey)
                .privateKey(privateKey)
                .build();

        final BankAccountListResponse br = billingoClient.getBankAccounts();

        System.out.println("====");

        final ClientListResponse clientList = billingoClient.getClients(null, null);

        System.out.println("====");

        if (clientList.getData() != null && clientList.getData().size() > 0) {
            billingoClient.getClient(clientList.getData().get(0).getId());

            System.out.println("====");
        }

        final Client client = new Client();
        client.setName("Gigazoom LLC.");
        client.setEmail("rbrooks5@amazon.com");
        client.setTaxcode("123456");
        client.setForce(Boolean.FALSE);

        final BillingAddress ba = new BillingAddress();
        ba.setStreetName("Moulton");
        ba.setStreetType("Terrace");
        ba.setHouseNr("2797");
        ba.setBlock("A");
        ba.setEntrance("B");
        ba.setFloor("3.");
        ba.setDoor("17");
        ba.setCity("Preston");
        ba.setPostcode("PR1");
        ba.setDistrict("XII");
        ba.setCountry("United Kingdom");
        client.setBillingAddress(ba);

        client.setPhone("");

        final Bank bank = new Bank();
        bank.setAccountNumber("12345678-12345678-12345678");
        bank.setAccountIban("");
        bank.setAccountSwift("");
        client.setBank(bank);

        client.setLedgerAccountNumber("");
        client.setType("2");

        final ClientDefaults defaults = new ClientDefaults();

        defaults.setPaymentMethod("1");
        defaults.setElectronicInvoice("0");
        defaults.setInvoiceDueDays("3");
        defaults.setInvoiceCurrency("HUF");
        defaults.setInvoiceTemplateLangCode("hu");
        client.setDefaults(defaults);

        final ClientResponse cr = billingoClient.createClient(client);

        System.out.println("====");

        client.setName("Gigazoom Ltd");
        billingoClient.updateClient(client, cr.getData().getId());

        System.out.println("====");

        billingoClient.deleteClient(cr.getData().getId());

        System.out.println("====");

        billingoClient.getCurrency("EUR", "HUF", 1.0);

        System.out.println("====");

        billingoClient.getExpenseCategories("hu");

        System.out.println("====");

        final ExpenseListResponse er = billingoClient.getExpenses(null, null);

        System.out.println("====");

        if (er.getData() == null || er.getData().isEmpty()) {
            final Expense expense = new Expense();
            expense.setCategoriesId(2L);
            expense.setName("Office Supplies");
            expense.setInvoiceNo("2019-000002");
            expense.setClientUid(cr.getData().getId());
            expense.setGrossPrice(1000.0);
            expense.setVat(1L);
            expense.setCurrency("HUF");
            expense.setDueDate("2017-01-15");

            billingoClient.createExpense(expense);
        } else {
            final Long expenseId = er.getData().get(0).getId();
            final Expense expense = er.getData().get(0).getAttributes();

            expense.setName("Office Supplies Updated");
            billingoClient.updateExpense(expense, expenseId);
        }

        System.out.println("====");

        final InvoiceListResponse ir = billingoClient.getInvoices(null, null);

        System.out.println("====");

        if (ir.getData() != null && ir.getData().size() > 0) {
            billingoClient.getInvoice(ir.getData().get(0).getId());

            System.out.println("====");

            billingoClient.getInvoiceDownloadCode(ir.getData().get(0).getId());

            System.out.println("====");

            billingoClient.getInvoiceFromProforma(ir.getData().get(0).getId());

            System.out.println("====");

            billingoClient.getInvoicePdf(ir.getData().get(0).getId());

            System.out.println("====");

            billingoClient.sendInvoice(ir.getData().get(0).getId());

            System.out.println("====");

            final InvoicePayment payment = new InvoicePayment();
            payment.setDate("2019-09-21");
            payment.setAmount(2000.0);
            payment.setPaymentMethod(2L);

            billingoClient.updateInvoicePayment(ir.getData().get(0).getId(), payment);

            System.out.println("====");

            billingoClient.undoInvoicePayment(ir.getData().get(0).getId());

            System.out.println("====");

            billingoClient.cancelInvoice(ir.getData().get(0).getId());

            System.out.println("====");
        }

        billingoClient.getInvoiceBlocks();

        System.out.println("====");

        final InvoiceNew newInvoice = new InvoiceNew();
        newInvoice.setFulfillmentDate("2015-12-12");
        newInvoice.setDueDate("2015-12-20");
        newInvoice.setPaymentMethod(1L);
        newInvoice.setComment("");
        newInvoice.setTemplateLangCode("hu");
        newInvoice.setElectronic_invoice(0L);
        newInvoice.setCurrency("HUF");
        newInvoice.setExchange_rate(null);
        newInvoice.setClientUid(cr.getData().getId());
        newInvoice.setBlockUid(0L);
        newInvoice.setType(3L);
        newInvoice.setRoundTo(0L);
        newInvoice.setBankAccountUid(br.getData().get(0).getId());

        final InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setDescription("Test Item");
        invoiceItem.setVatId(1L);
        invoiceItem.setQty(1.0);
        invoiceItem.setNetUnitPrice(3500.0);
        invoiceItem.setUnit("pc");
        invoiceItem.setItemComment("Item comment");

        final List<InvoiceItem> items = new ArrayList<>();
        items.add(invoiceItem);

        newInvoice.setItems(items);

        billingoClient.createInvoice(newInvoice);

        System.out.println("====");

        billingoClient.getPaymentMethod("hu");

        System.out.println("====");

        billingoClient.getVats();

        System.out.println("====");

        billingoClient.getVatEu("DE", "104.20.46.161", "DE", null);

        System.out.println("====");
    }

```
