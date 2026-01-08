<script lang="ts">
  import { onMount } from "svelte";
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

  const API_BASE_URL = "https://eportfolio-3b6q.onrender.com";

  let portfolio = $state<Portfolio | null>(null);
  let error = $state<string | null>(null);
  let loading = $state<boolean>(true);

  async function wakeBackend() {
    try {
      await fetch(API_BASE_URL, { cache: "no-store" });
    } catch {
      // backend likely waking up , ignore
    }
  }


 async function loadPortfolio() {
    try {
      const res = await fetch(`${API_BASE_URL}/api/portfolio`);
      if (!res.ok) throw new Error("Failed to load portfolio");
      portfolio = await res.json();
    } catch (e) {
      error = e.message;
    } finally {
      loading = false;
    }
  }

  onMount(async () => {
    await wakeBackend();     // wake Render
    await loadPortfolio();  // fetch data once backend is awake
  });
</script>

<Header />

<main>
  {#if loading}
    <div class="loading-screen">
      <div class="loading-card">
        <div class="loading-ring"></div>
        <p class="loading-title">Loading your portfolio</p>
        <p class="loading-subtitle">Waking up the server…</p>
      </div>
    </div>
  {/if}

  {#if !loading}
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
  {/if}
</main>

