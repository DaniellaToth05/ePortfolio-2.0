<script lang="ts">
  import Header from "./components/Header.svelte";
  import Hero from "./components/Hero.svelte";
  import ActionSection from "./components/ActionSection.svelte";
  import GoalsSection from "./components/GoalsSection.svelte";

  interface Investment {
    symbol: string;
    name: string;
    quantity: number;
    price: number;
    bookValue: number;
  }

  interface Portfolio {
    investments: Investment[];
  }

  let portfolio = $state<Portfolio | null>(null);
  let error = $state<string | null>(null);

  async function loadPortfolio() {
    try {
      const res = await fetch("http://localhost:8080/api/portfolio");
      if (!res.ok) throw new Error("Failed to load portfolio");
      portfolio = await res.json();
    } catch (e) {
      error = e.message;
    }
  }

  loadPortfolio();
</script>

<Header />

<main>
  <section id="dashboard">
    <Hero investments={portfolio?.investments ?? []} />
  </section>
  <section id="portfolio">
    <ActionSection
      investments={portfolio?.investments ?? []}
      loadPortfolio={loadPortfolio}
    />
  </section>
  <section id="goals">
    <GoalsSection investments={portfolio?.investments ?? []} />
  </section>
</main>

