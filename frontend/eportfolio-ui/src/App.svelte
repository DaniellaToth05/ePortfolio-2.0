<script>
  import Header from "./components/Header.svelte";
  import Hero from "./components/Hero.svelte";
  import ActionSection from "./components/ActionSection.svelte";
  import GoalsSection from "./components/GoalsSection.svelte";

  let portfolio = $state(null);
  let error = $state(null);

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
  <Hero investments={portfolio?.investments ?? []} />
  <ActionSection
    investments={portfolio?.investments ?? []}
    loadPortfolio={loadPortfolio}
  />
  <GoalsSection />
</main>

