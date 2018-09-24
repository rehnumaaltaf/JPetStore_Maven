import { OlamCTRMPage } from './app.po';

describe('cli-project App', () => {
  let page: OlamCTRMPage;

  beforeEach(() => {
    page = new OlamCTRMPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
